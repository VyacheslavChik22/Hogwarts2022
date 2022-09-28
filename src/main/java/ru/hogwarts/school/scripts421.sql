ALTER TABLE student
ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER TABLE student
ALTER COLUMN name TEXT UNIQUE NOT NULL;


ALTER TABLE student
ALTER COLUMN age SET DEFAULT 20;


ALTER TABLE faculty
ADD CONSTRAINT name_color_unique UNIQUE (name, color);





////////////////////////////////////////////////////////////////////////////////////////////
