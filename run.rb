#!/usr/bin/env ruby
#
if ARGV[0].nil? && ARGV[1].nil?
  file = "TestRun.txt"
  debug = 1
else
  file = ARGV[0]
  debug = ARGV[1] || 0
end
puts "File: #{file}"
`java haw.ci.Application #{file}`
exec "java -jar NewOBI.jar TestRun.txt #{debug}"

