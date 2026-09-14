package org.com.lab.services;

import org.com.lab.specification.DynamicSpecificationBuilder;
import org.com.lab.specification.SearchCriteria;
import org.com.lab.dto.request.CreateRecordRequest;
import org.com.lab.dto.response.RecordResponse;
import org.com.lab.entity.Channel;
import org.com.lab.entity.CrawledRecord;
import org.com.lab.entity.enums.ProcessingStatus;
import org.com.lab.error.LabErrorCode;
import org.com.lab.repository.ChannelJpaRepository;
import org.com.lab.repository.CrawledRecordJpaRepository;
import org.example.javaframework.infra.security.CurrentUserContext;
import org.example.javaframework.web.common.EnumConverter;
import org.example.javaframework.web.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServices {
    private final ChannelJpaRepository channelJpaRepository;

    private final CrawledRecordJpaRepository crawledRecordJpaRepository;

    private final UserServices userServices;

    private final int MAX_LIMIT = 100;

    public RecordServices(ChannelJpaRepository channelJpaRepository,
                          CrawledRecordJpaRepository crawledRecordJpaRepository,
                          UserServices userServices) {
        this.channelJpaRepository = channelJpaRepository;
        this.crawledRecordJpaRepository = crawledRecordJpaRepository;
        this.userServices = userServices;
    }

    public RecordResponse createRecord(CreateRecordRequest request) {
        String userId = CurrentUserContext.get().userId();
        if (channelJpaRepository.findAccessibleChannel(request.channelId(), userId).isEmpty()) {
            throw new BusinessException(LabErrorCode.CHANNEL_NOT_FOUND);
        }
        CrawledRecord crawledRecord = new CrawledRecord();
        crawledRecord.setChannelId(request.channelId());
        crawledRecord.setContent(request.content());
        crawledRecord.setTitle(request.title());
        crawledRecord.setPublishedAt(request.publishAt());
        crawledRecord.setCrawledAt(request.crawledAt());
        crawledRecord.setProcessingStatus(EnumConverter.fromString(ProcessingStatus.class, request.processingStatus()));
        crawledRecord.setErrorMessage(request.errorMessage());
        crawledRecordJpaRepository.save(crawledRecord);
        return RecordResponse.from(crawledRecord);
    }


    public Page<RecordResponse> getRecords(int page, int limit, Long domainId, Long channelId, String search) {
        if (page < 1) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, "page", page);
        }
        if (limit < 1 || limit > MAX_LIMIT) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, "limit", limit);
        }
        if (domainId == null) {
                throw new BusinessException(LabErrorCode.INVALID_INPUT, "domainId", "required");
        }

            String userId = CurrentUserContext.get().userId();
            List<Long> allowedDomainIds = userServices.getActiveDomainOfUser(userId);

            if (!allowedDomainIds.contains(domainId)) {
                throw new BusinessException(LabErrorCode.DOMAIN_ACCESS_DENIED, domainId);
            }
            List<Long> channelIdsInDomain = channelJpaRepository.findByDomainId(domainId)
                .stream()
                .map(Channel::getId)
                .toList();

            if (channelIdsInDomain.isEmpty()) {
                return Page.empty(PageRequest.of(page - 1, limit));
            }


        List<SearchCriteria> criteria = new ArrayList<>();

        if (channelId != null) {
            criteria.add(new SearchCriteria("channelId", "eq", channelId));
        } else {
            criteria.add(new SearchCriteria("channelId", "in", channelIdsInDomain));
        }

        if (search != null && !search.isBlank()) {
            criteria.add(new SearchCriteria("title", "like", search.trim()));
        }

        Specification<CrawledRecord> spec = DynamicSpecificationBuilder.build(criteria);
        Pageable pageable = PageRequest.of(page - 1, limit);

            return crawledRecordJpaRepository.findAll(spec, pageable).map(RecordResponse::from);
        }


    }
