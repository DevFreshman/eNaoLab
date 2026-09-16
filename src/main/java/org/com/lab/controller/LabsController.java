package org.com.lab.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.com.lab.dto.request.CreateDomainRequest;
import org.com.lab.dto.request.CreateRecordRequest;
import org.com.lab.dto.response.*;
import org.com.lab.services.*;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class LabsController {

    private final StatsServices statsService;
    private final UserServices userServices;
    private final DomainServices domainServices;
    private final ChannelServices channelServices;
    private final RecordServices recordServices;

    public LabsController(StatsServices statsService,
                          UserServices userServices,
                          DomainServices domainServices,
                          ChannelServices channelServices,
                          RecordServices recordServices) {
        this.statsService = statsService;
        this.userServices = userServices;
        this.domainServices = domainServices;
        this.channelServices = channelServices;
        this.recordServices = recordServices;
    }

    // API A: Thống kê tổng quát
    @GetMapping("/public/lab/stats")
    public StatsResponse getStats() {
        return statsService.getStats();
    }

    // API B: Thông tin user hiện tại
    @GetMapping("/lab/me")
    public UserInfoResponse getMe() {
        return userServices.getUserInfo();
    }

    // API C: Admin tạo domain
    @PostMapping("/admin/domains")
    public DomainResponse createDomain(@Valid @RequestBody CreateDomainRequest request) {
        return domainServices.createDomain(request);
    }

    // API D: Danh sách kênh
    @GetMapping("/lab/channels")
    public Page<ChannelResponse> listChannels(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "INVALID_INPUT") int page,
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "INVALID_INPUT") @Max(value = 100, message = "INVALID_LIMIT") int limit,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) @Positive(message = "INVALID_INPUT") Long domainId
    ) {
        return channelServices.listChannels(page, limit, search, domainId);
    }

    // API E: Tạo bản ghi thu thập
    @PostMapping("/lab/records")
    public RecordResponse createRecord(@Valid @RequestBody CreateRecordRequest request) {
        return recordServices.createRecord(request);
    }

    // API F: Danh sách bản ghi theo domain
    @GetMapping("/lab/records")
    public Object listRecords(
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "INVALID_INPUT") Integer page,
            @RequestParam(defaultValue = "20") @Min(value = 1, message = "INVALID_INPUT") @Max(value = 100, message = "INVALID_LIMIT") Integer limit,
            @RequestParam @NotNull(message = "INVALID_INPUT") @Positive(message = "INVALID_INPUT") Long domainId,
            @RequestParam(required = false) @Positive(message = "INVALID_INPUT") Long channelId,
            @RequestParam(required = false) String search
    ) {
        return recordServices.getRecords(page, limit, domainId, channelId, search);
    }
}