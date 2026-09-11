package org.com.lab.repository;

import org.com.lab.entity.CrawledRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CrawledRecordJpaRepository extends JpaRepository<CrawledRecord, Long>, JpaSpecificationExecutor<CrawledRecord> {
}
