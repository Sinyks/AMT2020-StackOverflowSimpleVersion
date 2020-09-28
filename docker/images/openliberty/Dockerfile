FROM openliberty/open-liberty
MAINTAINER Guillaume Zaretti
COPY apps/* /config/dropins/
COPY configs/server.xml /config/
ARG VERBOSE=false
RUN configure.sh

