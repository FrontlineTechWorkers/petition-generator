#!/usr/bin/env swift
import Darwin
import Foundation

let arguments = Process.arguments

/*
	read input parameters, should have 2 input arguments, filename and count
	basename is the first argument
*/
if arguments.count < 3 {
	println("Usage: ./PetitionGenerator.swift [input file name] [count]")
	exit(1)
}

let filename = arguments[1]
let count = arguments[2].toInt()

var error: NSError?
let fileContent = String(contentsOfFile: filename, encoding: NSUTF8StringEncoding, error:&error)

if (error != nil) {
	println("ERROR: failed to read file \(filename)")
	exit(1)
}

var lines = fileContent!.componentsSeparatedByString("\n")
var firstNames: [String] = []
var lastNames: [String] = []

for line:NSString in lines {
	var length = line.length

	if (length < 0) {
		continue
	}

	if length > 3 {
		lastNames.append(line.substringToIndex(2))
		firstNames.append(line.substringFromIndex(2))
	} else {
		lastNames.append(line.substringToIndex(1))
		firstNames.append(line.substringFromIndex(1))
	}
}

// print a name from picking a char from last name and 2 chars from first name randomly, 3% chance to get only 1 char from first name
var i:Int = 0;
for i=0; i<(count!); i++ {
	println(lastNames[Int(arc4random_uniform(UInt32(lastNames.count)))] + firstNames[Int(arc4random_uniform(UInt32(firstNames.count)))])
}
