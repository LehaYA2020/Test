DROP TABLE IF EXISTS companies;

CREATE TABLE companies (
    id              int4  NOT NULL GENERATED ALWAYS AS IDENTITY,
    name            varchar NOT NULL,
    staff_count     int4,
    PRIMARY KEY (id)
);