CREATE TABLE complaints (
  id BIGSERIAL PRIMARY KEY,
  case_ref VARCHAR(40) NOT NULL UNIQUE,
  institution_id VARCHAR(80) NOT NULL,
  institution VARCHAR(240) NOT NULL,
  title VARCHAR(300) NOT NULL,
  bureau VARCHAR(160) NOT NULL,
  category VARCHAR(120) NOT NULL,
  priority VARCHAR(30) NOT NULL,
  status VARCHAR(40) NOT NULL,
  officer VARCHAR(160),
  due_date DATE
);
CREATE INDEX idx_complaint_institution ON complaints(institution_id);
CREATE INDEX idx_complaint_status ON complaints(status);
