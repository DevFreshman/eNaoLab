package org.com.lab.controller;
import org.com.lab.dto.request.CreateDomainRequest;
import org.com.lab.dto.request.CreateRecordRequest;
import org.com.lab.dto.response.*;
import org.com.lab.services.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
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

    // API A: thống kê tổng quát, không token — GET /public/lab/stats
    // trả về số lượng user, domain, channel, record
    @GetMapping("/public/lab/stats")
    public StatsResponse getStats() {
        return statsService.getStats();
    }

    // API B: thông tin user hiện tại, lấy từ CurrentUserContext (Redis session) — GET /lab/me
    @GetMapping("/me")
    public UserInfoResponse getMe() {
        return userServices.getUserInfo();
    }

    // API C: chỉ admin tạo domain — POST /lab/domains
    @PostMapping("admin/domains")
    public DomainResponse createDomain(@RequestBody CreateDomainRequest request ) {
        return domainServices.createDomain(request);
    }

    // API D: danh sách kênh, có phân trang/filter/search, chỉ trong domain user được gán
    // GET /lab/channels?page=1&limit=10&search=abc&domainId=1
    @GetMapping("/lab/channels")
    public Page<ChannelResponse> listChannels(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long domainId
    ) {
        return channelServices.listChannels(page, limit, search, domainId);
    }

    // API E: tạo bản ghi thu thập gắn kênh — POST /lab/records
    @PostMapping("create/records")
    public RecordResponse createRecord(@RequestBody CreateRecordRequest request ) {
        return recordServices.createRecord(request);
    }

    // API F: danh sách bản ghi, theo domain (bắt buộc) + kênh (tuỳ chọn), search theo tiêu đề
    // GET /lab/records?page=1&domainId=1&channelId=2&search=abc
    @GetMapping("lab/domain/records")
    public Object listRecords(
            @RequestParam(defaultValue = "1") Integer page,

            @RequestParam(defaultValue = "20") Integer limit,

            @RequestParam Long domainId,

            @RequestParam(required = false) Long channelId,

            @RequestParam(required = false) String search
    ) {
        return recordServices.getRecords(page, limit, domainId, channelId, search);
    }
}
