

Prometheus Offers a Query Language called 'PromQL' That Allows Users to 'select and aggregate
time series data in real-time.' The results can be displayed as 'graphs', viewed in 
'tabular form', or accessed by external systems through the 'HTTP API.'

 Examples:
 --------

1.Expression Language Data Types:

In Prometheuss expression language, an expression or sub-expression can evaluate to one of 
four types:

.Instant Vector:  Set Of Time Series Containing a 'single sample' for 'each time series,
 all sharing the same timestamp'
.Range Vector: Set Of Time Series Containing 'a range of data points over time for 
each time series'
.Scalar -  Simple Numeric Floating Point 'value'
.String -  'simple string value; currently unused'


2. Literals:

2.1: String Literals:

String literals in PromQL can be enclosed in 'single quotes, double quotes, or backticks.'

 .Single And double-Quoted Strings Use 'backslashes for escape sequences, similar to Go, 
allowing special characters and notations like octal or hexadecimal.'

 .In Contrast, Strings Within 'backticks do not parse escape characters, and newlines are 
preserved. Examples include:
'

"this is a string"
'these are unescaped: \n \\ \t'
`these are not unescaped: \n ' " \t`                  '

2.1 Float Literals:






