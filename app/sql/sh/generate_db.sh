#!/bin/bash
# This is a comment
sleep 1
PGPASSWORD=$1 psql personservice -U dev -a -f /ddl/person.sql
