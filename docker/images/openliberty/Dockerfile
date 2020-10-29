FROM openliberty/open-liberty:kernel-java11-openj9-ubi


COPY --chown=1001:0 \
    configs/ \
    /config

COPY --chown=1001:0 \
    apps/* \
    /config/apps

RUN configure.sh
