

1.Local Storage:

On-Disk Layout: Prometheus stores data in a custom format, organizing ingested samples into 
two-hour blocks with associated metadata and index files. A write-ahead log (WAL) secures data 
in case of crashes.

Compaction: Over time, Prometheus compacts two-hour blocks into larger blocks, with a limit
based on retention time or 31 days.

Operational Considerations: Users can configure storage paths, retention time, and size. 

Retention size should be 80-85% of allocated disk space to avoid storage overflow. 
Corrupted storage can be fixed by removing the storage directory or individual block directories.

'Limitations: Local storage is not clustered or replicated, so it is not scalable or durable 
for long-term storage. Backups via snapshots are recommended.'


2.Remote Storage Integrations:

'Prometheus can write to and read from remote storage systems using standardized formats. 
These integrations are not yet stable APIs and may evolve to use gRPC over HTTP/2.'

Backfilling From OpenMetrics Format:

Usage: Users can backfill data into Prometheus using OpenMetrics format through the promtool 
command. This is useful for migrating data from other systems. Care must be taken not to 
backfill data from the last three hours, as it might overlap with current data.

Backfilling for Recording Rules: Historical data for new recording rules can be created, 
though certain limitations exist, such as the inability to handle rules dependent on previous 
results within the same group.

Key Recommendations:
Use snapshots for backups.
Be cautious with retention settings to avoid data loss.
Use local filesystems for reliability, as NFS and non-POSIX filesystems are not supported.





