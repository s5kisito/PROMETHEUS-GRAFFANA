

https://prometheus.io/docs/prometheus/latest/configuration/unit_testing_rules/#series

You can use promtool to test your rules.

# For  Single Test File:
'   ./promtool test rules test.yml  '

# If you have Multiple Test Files:
'   ./promtool test rules test1.yml test2.yml test3.yml  '


Test File Format:
<test_group>
<series>
<alert_test_case>
<alert>
<promql_test_case>
<sample>

Example:
test.yml
alerts.yml