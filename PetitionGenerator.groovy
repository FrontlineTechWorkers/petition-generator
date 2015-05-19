// read input parameters
def cli = new CliBuilder(usage: 'PetitionGenerator.groovy [input file name] [count]')
def options = cli.parse(args)
def extraArguments = options.arguments()

// it takes 2 input parameters, filename and count
if (extraArguments == null || extraArguments.size() != 2) {
	cli.usage()
	return
}
def filename = extraArguments[0]
def count = extraArguments[1].toInteger()

String[] rawnames = new File(filename)
def firstname = []
def lastname = []
Random rand = new Random()

// print a name from picking a char from last name and 2 chars from first name randomly
def createResult = {
	println lastname.get(rand.nextInt(lastname.size())) +
	firstname.get(rand.nextInt(firstname.size())) +
	firstname.get(rand.nextInt(firstname.size()))
}

// convert the raw name list into an array of last name and first name respectively
rawnames.each {
	lastname.add(it.getAt(0))

	it.substring(1).each {
		firstname.add(it)
	}
}

// print 
count.times(createResult)

