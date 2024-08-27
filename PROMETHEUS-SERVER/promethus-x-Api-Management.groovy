Prometheus provides a set of management APIs for automation and integration:

Health Check (GET or HEAD /-/healthy): Always returns a 200 status, indicating Prometheus is 
healthy.

Readiness Check (GET or HEAD /-/ready): Returns a 200 status when Prometheus is 
ready to handle traffic.

Reload Configuration (PUT or POST /-/reload): Triggers a reload of Prometheus 
configuration and rule files. Disabled by default; enable via --web.enable-lifecycle flag. 
Alternatively, send a SIGHUP to reload.

Graceful Shutdown (PUT or POST /-/quit): Initiates a graceful shutdown of Prometheus. 
Disabled by default; enable via --web.enable-lifecycle flag. Alternatively, send a SIGTERM 
to quit.