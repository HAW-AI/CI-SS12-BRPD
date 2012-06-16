#!/usr/bin/env ruby
#
if ARGV[0].nil? && ARGV[1].nil?
  file = "TestRun.txt"
  debug = 1
elsif !ARGV[0].nil? && ARGV[1].nil?
  file = "TestRun.txt"
  debug = ARGV[0] || 0
else
  file = ARGV[0]
  debug = ARGV[1] || 0
end
puts "File: #{file}"
exec "java -jar NewOBI.jar #{file} #{debug}"

