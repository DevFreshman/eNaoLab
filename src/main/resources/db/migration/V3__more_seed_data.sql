-- ============================================================
-- LAB DATABASE
-- Migration: V3
-- Description: Additional fake data (more channels + records for pagination testing)
-- Database: Oracle
-- ============================================================

-- ============================================================
-- 1. Thêm CHANNEL — bổ sung cho NEWS, FINANCE để đủ nhiều lựa chọn
-- ============================================================

INSERT INTO LAB_CHANNEL (DOMAIN_ID, NAME, PLATFORM_TYPE, STATUS, SOURCE_URL, CONFIG, CREATED_BY)
SELECT ID, 'Tuoi Tre News', 'WEBPAGE', 'RUNNING', 'https://tuoitre.vn', '{"crawlIntervalMinutes":30}', 'USR000000000000000000001'
FROM LAB_DOMAIN WHERE CODE = 'NEWS';

INSERT INTO LAB_CHANNEL (DOMAIN_ID, NAME, PLATFORM_TYPE, STATUS, SOURCE_URL, CONFIG, CREATED_BY)
SELECT ID, 'Dan Tri YouTube', 'YOUTUBE', 'RUNNING', 'https://youtube.com/@dantri', '{"crawlIntervalMinutes":60}', 'USR000000000000000000001'
FROM LAB_DOMAIN WHERE CODE = 'NEWS';

INSERT INTO LAB_CHANNEL (DOMAIN_ID, NAME, PLATFORM_TYPE, STATUS, SOURCE_URL, CONFIG, CREATED_BY)
SELECT ID, 'CafeF Finance', 'WEBPAGE', 'RUNNING', 'https://cafef.vn', '{"crawlIntervalMinutes":15}', 'USR000000000000000000005'
FROM LAB_DOMAIN WHERE CODE = 'FINANCE';

INSERT INTO LAB_CHANNEL (DOMAIN_ID, NAME, PLATFORM_TYPE, STATUS, SOURCE_URL, CONFIG, CREATED_BY)
SELECT ID, 'Sports Facebook Page', 'FACEBOOK', 'DRAFT', 'https://facebook.com/example-sports', '{"crawlIntervalMinutes":90}', 'USR000000000000000000003'
FROM LAB_DOMAIN WHERE CODE = 'SPORT';


-- ============================================================
-- 2. Thêm nhiều CRAWLED_RECORD cho 'VnExpress Technology' (đủ để test phân trang, > 10 dòng)
-- ============================================================

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Cloud computing adoption accelerates in 2026', 'Enterprises continue shifting workloads to cloud infrastructure.', SYSTIMESTAMP - INTERVAL '10' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '10' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'New JavaScript framework released', 'A new frontend framework promises faster rendering performance.', SYSTIMESTAMP - INTERVAL '9' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '9' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Quantum computing breakthrough announced', 'Researchers report progress in stable qubit architecture.', SYSTIMESTAMP - INTERVAL '8' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '8' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Cybersecurity threats on the rise', 'Security researchers warn of increasing ransomware attacks.', SYSTIMESTAMP - INTERVAL '7' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '7' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Open source project reaches milestone', 'A popular open source project celebrates its 10th anniversary.', SYSTIMESTAMP - INTERVAL '6' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '6' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Mobile app development trends', 'Cross-platform frameworks continue to gain popularity among developers.', SYSTIMESTAMP - INTERVAL '5' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '5' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Database performance tuning tips', 'Experts share best practices for optimizing query performance.', SYSTIMESTAMP - INTERVAL '4' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '4' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Microservices architecture explained', 'A deep dive into designing scalable microservices systems.', SYSTIMESTAMP - INTERVAL '3' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '3' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'API gateway design patterns', 'Comparing common patterns for managing API traffic at scale.', SYSTIMESTAMP - INTERVAL '2' DAY, 'ERROR', 'Parsing error in response body', SYSTIMESTAMP - INTERVAL '2' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Edge computing use cases in 2026', 'Companies explore edge computing for latency-sensitive applications.', SYSTIMESTAMP - INTERVAL '1' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '1' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'AI coding assistants reshape workflows', 'Developers increasingly rely on AI tools for code generation.', SYSTIMESTAMP - INTERVAL '10' HOUR, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '10' HOUR FROM LAB_CHANNEL WHERE NAME = 'VnExpress Technology';


-- ============================================================
-- 3. Thêm CRAWLED_RECORD cho các channel mới (Tuoi Tre, CafeF...)
-- ============================================================

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Traffic accident reported downtown', 'Local authorities are investigating the cause of the incident.', SYSTIMESTAMP - INTERVAL '4' HOUR, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '4' HOUR FROM LAB_CHANNEL WHERE NAME = 'Tuoi Tre News';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'City announces new public transport plan', 'The plan aims to reduce congestion in urban areas.', SYSTIMESTAMP - INTERVAL '3' HOUR, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '3' HOUR FROM LAB_CHANNEL WHERE NAME = 'Tuoi Tre News';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Interest rates expected to remain stable', 'Analysts predict no major changes in the coming quarter.', SYSTIMESTAMP - INTERVAL '2' HOUR, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '2' HOUR FROM LAB_CHANNEL WHERE NAME = 'CafeF Finance';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Gold prices fluctuate amid global uncertainty', 'Market volatility continues to affect precious metal prices.', SYSTIMESTAMP - INTERVAL '1' HOUR, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '1' HOUR FROM LAB_CHANNEL WHERE NAME = 'CafeF Finance';


-- ============================================================
-- 4. Thêm CRAWLED_RECORD cho SPORT (VnExpress Sports) — user tranbinh test riêng
-- ============================================================

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'National team announces new coach', 'The federation confirms the appointment of a new head coach.', SYSTIMESTAMP - INTERVAL '5' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '5' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Sports';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Local club wins regional championship', 'The team celebrates a hard-fought victory in the final match.', SYSTIMESTAMP - INTERVAL '4' DAY, 'PROCESSED', NULL, SYSTIMESTAMP - INTERVAL '4' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Sports';

INSERT INTO LAB_CRAWLED_RECORD (CHANNEL_ID, TITLE, CONTENT, PUBLISHED_AT, PROCESSING_STATUS, ERROR_MESSAGE, CREATED_AT)
SELECT ID, 'Star player transfers to new team', 'The transfer fee sets a new record for the league.', SYSTIMESTAMP - INTERVAL '3' DAY, 'NEW', NULL, SYSTIMESTAMP - INTERVAL '3' DAY FROM LAB_CHANNEL WHERE NAME = 'VnExpress Sports';


-- ============================================================
-- 5. Gán thêm user-domain (để test case N-N nhiều domain thật sự)
-- ============================================================

-- nguyenan (đã có NEWS, TECH) — thêm FINANCE để test user có 3 domain
INSERT INTO LAB_USER_DOMAIN (USER_ID, DOMAIN_ID, ASSIGNED_BY)
SELECT 'USR000000000000000000002', ID, 'USR000000000000000000001'
FROM LAB_DOMAIN WHERE CODE = 'FINANCE';

-- tranbinh (đã có SPORT) — thêm NEWS để test giao nhau domain giữa nhiều user
INSERT INTO LAB_USER_DOMAIN (USER_ID, DOMAIN_ID, ASSIGNED_BY)
SELECT 'USR000000000000000000003', ID, 'USR000000000000000000001'
FROM LAB_DOMAIN WHERE CODE = 'NEWS';


COMMIT;