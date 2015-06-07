#!/usr/bin/env ruby

# read input parameters, should have 2 input parameters, filename and count
if ARGV.length < 2
  puts 'Usage: ./PetitionGenerator.rb [input file name] [count]'
  Process.exit(1)
end

filename = ARGV.shift
count = ARGV.shift.to_i

fileObj = File.open(filename, 'r')

firstName = Array.new
lastName = Array.new

# read the rawdata file
fileObj.each_line do |line|
	line.chomp!
	if (line.size > 0)

		# quick hack to detect 複姓
		if line.length > 3
			lastName.push(line[0..1])
			firstName.push(line[2..-1])
		else
			lastName.push(line[0])
			firstName.push(line[1..-1])
		end
	end
end

# print a name from picking a char from last name and 2 chars from first name randomly, 3% chance to get only 1 char from first name
(1..count).each {|i|
	name = lastName[rand(lastName.size)] + firstName[rand(firstName.size)]

	puts name
}
