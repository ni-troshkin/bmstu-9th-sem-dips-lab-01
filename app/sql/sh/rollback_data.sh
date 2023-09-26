#!/bin/bash
# This is a comment
PGPASSWORD=$1 psql personservice -U dev -a -f /dml/truncate_tables.sql

