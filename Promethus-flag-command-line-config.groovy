
' ./prometheus -h ' 

Flags:
  -h, --[no-]help                Show context-sensitive help (also try --help-long 
                                 and --help-man).

      --[no-]version             Show application version.

      --config.file="prometheus.yml"  
                                 Prometheus configuration file path.

      --web.listen-address="0.0.0.0:9090"  
                                 Address to listen on for UI, API, and telemetry.

      --auto-gomemlimit.ratio=0.9  
                                 The ratio of reserved GOMEMLIMIT memory to the detected 
                                 maximum container or system memory

      --web.config.file=""       [EXPERIMENTAL] Path to configuration file that can enable 
      TLS or authentication.

      --web.read-timeout=5m      Maximum duration before timing out read of the request, 
      and closing idle connections.

      --web.max-connections=512  Maximum number of simultaneous connections.

      --web.external-url=<URL>   The URL under which Prometheus is externally reachable
       (for example, if Prometheus is served via a reverse proxy). Used for generating 
       relative and absolute links back to Prometheus itself. 
       If the URL has a path portion, it will be used to prefix all HTTP endpoints served 
       by Prometheus. If omitted, relevant URL components will be derived automatically.

      --web.route-prefix=<path>  Prefix for the internal routes of web endpoints. 
      Defaults to path of --web.external-url.

      --web.user-assets=<path>   Path to static asset directory, available at /user.
      
      --[no-]web.enable-lifecycle  
                                 Enable shutdown and reload via HTTP request.

      --[no-]web.enable-admin-api  
                                 Enable API endpoints for admin control actions.

      --[no-]web.enable-remote-write-receiver  
                                 Enable API endpoint accepting remote write requests.

      --web.remote-write-receiver.accepted-protobuf-messages=prometheus.WriteRequest... ...  
                                 List of the remote write protobuf messages to accept when 
                                 receiving the remote writes. Supported values: 
                                 prometheus.WriteRequest, io.prometheus.write.v2.Request

      --web.console.templates="consoles"  
                                 Path to the console template directory, available at /consoles.

      --web.console.libraries="console_libraries"  
                                 Path to the console library directory.

      --web.page-title="Prometheus Time Series Collection and Processing Server"  
                                 Document title of Prometheus instance.

      --web.cors.origin=".*"     Regex for CORS origin. It is fully anchored.
       Example: 'https?://(domain1|domain2)\.com'

      --storage.tsdb.path="data/"  
                                 Base path for metrics storage. Use with server mode only.

      --storage.tsdb.retention=STORAGE.TSDB.RETENTION  
                                 [DEPRECATED] How long to retain samples in storage. 
                                 This flag has been deprecated, 
                                 use "storage.tsdb.retention.time" instead. 
                                 Use with server mode only.

      --storage.tsdb.retention.time=STORAGE.TSDB.RETENTION.TIME  
                                 How long to retain samples in storage. 
                                 When this flag is set it overrides "storage.tsdb.retention". 
                                 If neither this flag nor "storage.tsdb.retention" nor
                                 "storage.tsdb.retention.size" is set, 
                                 the retention time defaults to 15d. 
                                 Units Supported: y, w, d, h, m, s, ms. 
                                 Use with server mode only.

      --storage.tsdb.retention.size=STORAGE.TSDB.RETENTION.SIZE  
                                 Maximum number of bytes that can be stored for blocks. 
                                 A unit is required, 
                                 supported units: B, KB, MB, GB, TB, PB, EB. Ex: "512MB".
                                  Based on powers-of-2, so 1KB is 1024B. Use with
                                 server mode only.

      --[no-]storage.tsdb.no-lockfile  
                                 Do not create lockfile in data directory. 
                                 Use with server mode only.

      --storage.tsdb.head-chunks-write-queue-size=0  
                                 Size of the queue through which head chunks are written to 
                                 the disk to be m-mapped, 0 disables the queue completely. 
                                 Experimental. Use with server mode only.

      --storage.agent.path="data-agent/"  
                                 Base path for metrics storage. Use with agent mode only.

      --[no-]storage.agent.wal-compression  
                                 Compress the agent WAL. Use with agent mode only.

      --storage.agent.retention.min-time=STORAGE.AGENT.RETENTION.MIN-TIME  
                                 Minimum age samples may be before being considered 
                                 for deletion when the WAL is truncated Use 
                                 with agent mode only.

      --storage.agent.retention.max-time=STORAGE.AGENT.RETENTION.MAX-TIME  
                                 Maximum age samples may be before being forcibly deleted 
                                 when the WAL is truncated Use with agent mode only.

      --[no-]storage.agent.no-lockfile  
                                 Do not create lockfile in data directory. 
                                 Use with agent mode only.

      --storage.remote.flush-deadline=<duration>  
                                 How long to wait flushing sample on shutdown or config reload.

      --storage.remote.read-sample-limit=5e7  
                                 Maximum overall number of samples to return via the remote
                                read interface, in a single query. 0 means no limit. 
                                This limit is ignored for streamed response types. 
                                Use with server
                                 mode only.

      --storage.remote.read-concurrent-limit=10  
                                 Maximum number of concurrent remote read calls. 
                                 0 means no limit. Use with server mode only.

      --storage.remote.read-max-bytes-in-frame=1048576  
                                 Maximum number of bytes in a single frame for
                                streaming remote read response types before marshalling. 
                                Note that client might have limit on frame size as well. 
                                1MB as recommended by
                                 protobuf by default. Use with server mode only.

      --rules.alert.for-outage-tolerance=1h  
                                 Max time to tolerate prometheus outage for restoring "for" 
                                 state of alert. Use with server mode only.

      --rules.alert.for-grace-period=10m  
                                 Minimum duration between alert and restored "for" state. 
                                 This is maintained only for alerts with configured "for" 
                                 time greater than grace period. Use with server mode only.

      --rules.alert.resend-delay=1m  
                                 Minimum amount of time to wait before resending an alert 
                                 to Alertmanager. Use with server mode only.

      --rules.max-concurrent-evals=4  
                                 Global concurrency limit for independent rules that can run 
                                 concurrently. When set, "query.max-concurrency" may need to 
                                 be adjusted accordingly. Use with server mode only.

      --alertmanager.notification-queue-capacity=10000  
                                 The capacity of the queue for pending Alertmanager 
                                 notifications. Use with server mode only.

      --[no-]alertmanager.drain-notification-queue-on-shutdown  
                                 Send any outstanding Alertmanager notifications 
                                 when shutting down. If false, any outstanding 
                                 Alertmanager notifications will be dropped when shutting down.
                                  Use with server mode only.

      --query.lookback-delta=5m  The maximum lookback duration for retrieving metrics 
                                 during expression evaluations and federation.
                                 Use with server mode only.

      --query.timeout=2m         Maximum time a query may take before being aborted. 
                                 Use with server mode only.

      --query.max-concurrency=20  
                                 Maximum number of queries executed concurrently. 
                                 Use with server mode only.

      --query.max-samples=50000000  
                                 Maximum number of samples a single query can load into memory.
                                 Note that queries will fail if they try to load more samples 
                                 than this into memory, so this also limits the number of
                                 samples a query can return. Use with server mode only.

      --enable-feature= ...     Comma separated feature names to enable. Valid options: agent,
                                auto-gomemlimit, exemplar-storage, expand-external-labels,
                                memory-snapshot-on-shutdown, 
                                promql-per-step-stats, promql-experimental-functions, 
                                remote-write-receiver(DEPRECATED), extra-scrape-metrics, 
                                new-service-discovery-manager, auto-gomaxprocs, 
                                no-default-scrape-port, native-histograms,
                                otlp-write-receiver, created-timestamp-zero-ingestion, 
                                concurrent-rule-eval. 
                                See https://prometheus.io/docs/prometheus/latest/feature_flags/
                                for more details.

      --log.level=info           Only log messages with the given severity or above.
                                 One of: [debug, info, warn, error]

      --log.format=logfmt        Output format of log messages. One of: [logfmt, json]