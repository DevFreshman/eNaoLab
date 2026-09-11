-- ============================================================
-- LAB DATABASE
-- Migration: V2
-- Description: Fake / Seed Data
-- Database: Oracle
-- ============================================================


-- ============================================================
-- 1. LAB_USER
-- ============================================================

INSERT INTO LAB_USER (
    ID,
    USERNAME,
    EMAIL,
    PASSWORD_HASH,
    FULL_NAME,
    ROLE,
    STATUS
) VALUES (
             'USR000000000000000000001',
             'admin',
             'admin@lab.local',
             '$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ12',
             'System Administrator',
             'ADMIN',
             'ACTIVE'
         );

INSERT INTO LAB_USER (
    ID,
    USERNAME,
    EMAIL,
    PASSWORD_HASH,
    FULL_NAME,
    ROLE,
    STATUS
) VALUES (
             'USR000000000000000000002',
             'nguyenan',
             'nguyen.an@lab.local',
             '$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ12',
             'Nguyen Van An',
             'USER',
             'ACTIVE'
         );

INSERT INTO LAB_USER (
    ID,
    USERNAME,
    EMAIL,
    PASSWORD_HASH,
    FULL_NAME,
    ROLE,
    STATUS
) VALUES (
             'USR000000000000000000003',
             'tranbinh',
             'tran.binh@lab.local',
             '$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ12',
             'Tran Van Binh',
             'USER',
             'ACTIVE'
         );

INSERT INTO LAB_USER (
    ID,
    USERNAME,
    EMAIL,
    PASSWORD_HASH,
    FULL_NAME,
    ROLE,
    STATUS
) VALUES (
             'USR000000000000000000004',
             'leminh',
             'le.minh@lab.local',
             '$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ12',
             'Le Quang Minh',
             'USER',
             'INACTIVE'
         );

INSERT INTO LAB_USER (
    ID,
    USERNAME,
    EMAIL,
    PASSWORD_HASH,
    FULL_NAME,
    ROLE,
    STATUS
) VALUES (
             'USR000000000000000000005',
             'phamhoa',
             'pham.hoa@lab.local',
             '$2a$10$abcdefghijklmnopqrstuuABCDEFGHIJKLMNOPQRSTUVWXYZ12',
             'Pham Thi Hoa',
             'USER',
             'ACTIVE'
         );


-- ============================================================
-- 2. LAB_DOMAIN
-- ============================================================

INSERT INTO LAB_DOMAIN (
    CODE,
    NAME,
    STATUS,
    CREATED_BY
) VALUES (
             'NEWS',
             'News & Media',
             'ACTIVE',
             'USR000000000000000000001'
         );

INSERT INTO LAB_DOMAIN (
    CODE,
    NAME,
    STATUS,
    CREATED_BY
) VALUES (
             'TECH',
             'Technology',
             'ACTIVE',
             'USR000000000000000000001'
         );

INSERT INTO LAB_DOMAIN (
    CODE,
    NAME,
    STATUS,
    CREATED_BY
) VALUES (
             'SPORT',
             'Sports',
             'ACTIVE',
             'USR000000000000000000001'
         );

INSERT INTO LAB_DOMAIN (
    CODE,
    NAME,
    STATUS,
    CREATED_BY
) VALUES (
             'FINANCE',
             'Finance',
             'ACTIVE',
             'USR000000000000000000001'
         );

INSERT INTO LAB_DOMAIN (
    CODE,
    NAME,
    STATUS,
    CREATED_BY
) VALUES (
             'ENTERTAINMENT',
             'Entertainment',
             'INACTIVE',
             'USR000000000000000000002'
         );


-- ============================================================
-- 3. LAB_USER_DOMAIN
-- ============================================================

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY
)
SELECT
    'USR000000000000000000001',
    ID,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'NEWS';

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY
)
SELECT
    'USR000000000000000000002',
    ID,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'NEWS';

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY
)
SELECT
    'USR000000000000000000002',
    ID,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'TECH';

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY
)
SELECT
    'USR000000000000000000003',
    ID,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'SPORT';

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY
)
SELECT
    'USR000000000000000000005',
    ID,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'FINANCE';

INSERT INTO LAB_USER_DOMAIN (
    USER_ID,
    DOMAIN_ID,
    ASSIGNED_BY,
    REVOKED_AT,
    REVOKED_BY
)
SELECT
    'USR000000000000000000004',
    ID,
    'USR000000000000000000001',
    SYSTIMESTAMP - INTERVAL '2' DAY,
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'ENTERTAINMENT';


-- ============================================================
-- 4. LAB_CHANNEL
-- ============================================================

INSERT INTO LAB_CHANNEL (
    DOMAIN_ID,
    NAME,
    PLATFORM_TYPE,
    STATUS,
    SOURCE_URL,
    CONFIG,
    CREATED_BY
)
SELECT
    ID,
    'VnExpress Technology',
    'WEBPAGE',
    'RUNNING',
    'https://vnexpress.net/so-hoa',
    '{"crawlIntervalMinutes":30,"maxPages":10}',
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'TECH';

INSERT INTO LAB_CHANNEL (
    DOMAIN_ID,
    NAME,
    PLATFORM_TYPE,
    STATUS,
    SOURCE_URL,
    CONFIG,
    CREATED_BY
)
SELECT
    ID,
    'VnExpress Sports',
    'WEBPAGE',
    'RUNNING',
    'https://vnexpress.net/the-thao',
    '{"crawlIntervalMinutes":60,"maxPages":5}',
    'USR000000000000000000001'
FROM LAB_DOMAIN
WHERE CODE = 'SPORT';

INSERT INTO LAB_CHANNEL (
    DOMAIN_ID,
    NAME,
    PLATFORM_TYPE,
    STATUS,
    SOURCE_URL,
    CONFIG,
    CREATED_BY
)
SELECT
    ID,
    'Lab News Facebook',
    'FACEBOOK',
    'DRAFT',
    'https://facebook.com/example-news',
    '{"crawlIntervalMinutes":120}',
    'USR000000000000000000002'
FROM LAB_DOMAIN
WHERE CODE = 'NEWS';

INSERT INTO LAB_CHANNEL (
    DOMAIN_ID,
    NAME,
    PLATFORM_TYPE,
    STATUS,
    SOURCE_URL,
    CONFIG,
    CREATED_BY
)
SELECT
    ID,
    'Lab Tech YouTube',
    'YOUTUBE',
    'STOPPED',
    'https://youtube.com/@example-tech',
    '{"crawlIntervalMinutes":60}',
    'USR000000000000000000002'
FROM LAB_DOMAIN
WHERE CODE = 'TECH';

INSERT INTO LAB_CHANNEL (
    DOMAIN_ID,
    NAME,
    PLATFORM_TYPE,
    STATUS,
    SOURCE_URL,
    CONFIG,
    CREATED_BY
)
SELECT
    ID,
    'Finance News Portal',
    'WEBPAGE',
    'RUNNING',
    'https://example.com/finance',
    '{"crawlIntervalMinutes":45,"maxPages":20}',
    'USR000000000000000000005'
FROM LAB_DOMAIN
WHERE CODE = 'FINANCE';


-- ============================================================
-- 5. LAB_CRAWLED_RECORD
-- ============================================================

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'AI continues to transform software development',
    'Artificial intelligence is increasingly being integrated into modern software development workflows.',
    SYSTIMESTAMP - INTERVAL '2' DAY,
    'PROCESSED',
    NULL,
    SYSTIMESTAMP - INTERVAL '2' DAY
FROM LAB_CHANNEL
WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Spring Boot improves modern Java development',
    'Modern Spring Boot applications provide developers with a powerful platform for building backend services.',
    SYSTIMESTAMP - INTERVAL '1' DAY,
    'PROCESSED',
    NULL,
    SYSTIMESTAMP - INTERVAL '1' DAY
FROM LAB_CHANNEL
WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Failed crawling example',
    'This record represents a crawling failure.',
    SYSTIMESTAMP - INTERVAL '5' HOUR,
    'ERROR',
    'Connection timeout while crawling source',
    SYSTIMESTAMP - INTERVAL '5' HOUR
FROM LAB_CHANNEL
WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Football tournament begins this weekend',
    'The upcoming football tournament is expected to attract a large number of viewers.',
    SYSTIMESTAMP - INTERVAL '6' HOUR,
    'NEW',
    NULL,
    SYSTIMESTAMP - INTERVAL '6' HOUR
FROM LAB_CHANNEL
WHERE NAME = 'VnExpress Sports';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Technology trends in 2026',
    'Several technology trends are expected to influence software engineering in 2026.',
    SYSTIMESTAMP - INTERVAL '3' HOUR,
    'NEW',
    NULL,
    SYSTIMESTAMP - INTERVAL '3' HOUR
FROM LAB_CHANNEL
WHERE NAME = 'Lab Tech YouTube';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Stock market overview',
    'The financial market showed several notable movements during the trading session.',
    SYSTIMESTAMP - INTERVAL '1' HOUR,
    'PROCESSED',
    NULL,
    SYSTIMESTAMP - INTERVAL '1' HOUR
FROM LAB_CHANNEL
WHERE NAME = 'Finance News Portal';

INSERT INTO LAB_CRAWLED_RECORD (
    CHANNEL_ID,
    TITLE,
    CONTENT,
    PUBLISHED_AT,
    PROCESSING_STATUS,
    ERROR_MESSAGE,
    CREATED_AT
)
SELECT
    ID,
    'Crawler waiting for processing',
    'This record is waiting for the processing pipeline.',
    SYSTIMESTAMP - INTERVAL '30' MINUTE,
    'NEW',
    NULL,
    SYSTIMESTAMP - INTERVAL '30' MINUTE
FROM LAB_CHANNEL
WHERE NAME = 'Finance News Portal';


-- ============================================================
-- COMMIT
-- ============================================================

COMMIT;