\set csvdir `echo $CSVDIR`

\set copy_users '\\copy person from ' :csvdir 'person.csv' ' with (format csv);'

:copy_users

ALTER SEQUENCE public.person_id_person_seq RESTART 16;
