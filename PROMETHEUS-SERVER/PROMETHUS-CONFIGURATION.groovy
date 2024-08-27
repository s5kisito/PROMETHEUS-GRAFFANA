


Reference:
https://prometheus.io/docs/prometheus/latest/configuration/configuration/



Prometheus Configuration Involved Two (2) Parts:

- 'Command Line Flag' for Fixed System Such As:
 'storage locations, amount of data to keep on disk and in memory, etc'

Reference: Promethus-Flag-Command-Line-Config.Groovy


- 'Configuration File' for Defining Everything Related to Scraping:
 'jobs and their instances, as well as which rule files to load.' 
 (Rule_file, & Scraps_Configs)

Prometheus Configuration File has Three (3) Main "blocks":
'
- GLOBAL( )
 .SCRAPE INTERVAL
 .EVALUATION INTERVAL

- RULE_FILE (LOAD ALL RULES)

- SCRAPS_CONFIG (JOBS , INSTANCES, ENDPOINTS)'

However 'Additional Blocks' Can be added To 'the configuration file'.



Reference: 
*Promethus-Rule-file_Configuration.Groovy
*Promethus-Scraps_Configuration.Groovy
           

https://prometheus.io/docs/prometheus/latest/configuration/configuration/

