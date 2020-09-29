FROM openliberty/open-liberty
MAINTAINER Guillaume Zaretti
USER root
COPY --chown=1001:0 apps/* /config/dropins/
COPY --chown=1001:0 configs/server.xml /config/
RUN chown -R 1001:0 /config
RUN chown -R 1001:0 /config/dropins/
USER 1001
ARG VERBOSE=true
RUN configure.sh

