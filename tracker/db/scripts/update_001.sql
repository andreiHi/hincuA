CREATE TABLE IF NOT EXISTS tracker(
             id SERIAL PRIMARY KEY NOT NULL,
             name VARCHAR(50),
             description VARCHAR(100),
             create_date TIMESTAMP WITHOUT TIME ZONE);

CREATE TABLE IF NOT EXISTS comments(
              id SERIAL PRIMARY KEY not null,
              id_item INTEGER REFERENCES tracker(id),
              description TEXT,
              data_create TIMESTAMP);