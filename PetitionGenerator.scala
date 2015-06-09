import scala.io.Source
object PetitionGenerator{
    def usage() {
        Console.err.println("Usage: PetitionGenerator rawname.txt count")
    }

    def main(args: Array[String]) {
        if (args.length != 2) {
            usage()
            sys.exit(1)
        }
        val random = new scala.util.Random(System.nanoTime)
        val lastNames = scala.collection.mutable.Set[Char]()
        val firstNames = scala.collection.mutable.Set[Char]()
        val fileName = args(0)
        val count = args(1).toInt
        for (fullName <- Source.fromFile(fileName).getLines()) {
            val lastName = fullName(0)
            val firstName = fullName.drop(1)
            for (c <- firstName) firstNames += c
            lastNames += lastName
        }
        val firstNamesList = firstNames.toList
        val lastNamesList = lastNames.toList

        1 to count foreach {
            _ => 
            val fakeName = new StringBuilder
            fakeName += lastNamesList(random.nextInt(lastNamesList.size))
            fakeName += firstNamesList(random.nextInt(firstNamesList.size))
            fakeName += firstNamesList(random.nextInt(firstNamesList.size))
            println(s"$fakeName")
        }
    }
}
