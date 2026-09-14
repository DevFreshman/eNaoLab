package org.com.lab.services;

import org.com.lab.specification.DynamicSpecificationBuilder;
import org.com.lab.specification.SearchCriteria;
import org.com.lab.dto.response.ChannelResponse;
import org.com.lab.entity.Channel;
import org.com.lab.error.LabErrorCode;
import org.com.lab.repository.ChannelJpaRepository;
import org.example.javaframework.infra.security.CurrentUserContext;
import org.example.javaframework.web.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelServices {

    private static final int MAX_LIMIT = 100;

    private final ChannelJpaRepository channelRepository;
    private final UserServices userServices;

    public ChannelServices(ChannelJpaRepository channelRepository, UserServices userServices) {
        this.channelRepository = channelRepository;
        this.userServices = userServices;
    }

    public Page<ChannelResponse> listChannels(int page, int limit,
                                              String search, Long domainId) {

        if (page < 1) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, "page", page);
        }
        if (limit < 1 || limit > MAX_LIMIT) {
            throw new BusinessException(LabErrorCode.INVALID_INPUT, "limit", limit);
        }
        String userId = CurrentUserContext.get().userId();
        List<Long> allowedDomainIds = userServices.getActiveDomainOfUser(userId);

        if (allowedDomainIds.isEmpty()) {
            return Page.empty(PageRequest.of(page - 1, limit));
        }

        if (domainId != null && !allowedDomainIds.contains(domainId)) {
            throw new BusinessException(LabErrorCode.DOMAIN_ACCESS_DENIED, domainId);
        }

        List<Long> targetDomainIds = (domainId != null) ? List.of(domainId) : allowedDomainIds;

        List<SearchCriteria> criteria = new ArrayList<>();
        criteria.add(new SearchCriteria("domainId", "in", targetDomainIds));
        if (search != null && !search.isBlank()) {
            criteria.add(new SearchCriteria("name", "like", search.trim()));
        }

        Specification<Channel> spec = DynamicSpecificationBuilder.build(criteria);
        Pageable pageable = PageRequest.of(page - 1, limit);

        return channelRepository.findAll(spec, pageable).map(ChannelResponse::from);
    }
}
