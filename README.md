# petition-generator
Inspired by Robert Chow's pro-Beijing signature campaigns in Hong Kong that were caught on the camera showing some helpers each diligently emulating signatures for an imaginary supporter base, we came up with this productivity gainer for them.  If authenticity means nothing to them, why bother to sign manually?

Groovy

```sh
groovy PetitionGenerator.groovy [input file name] [count]
```

Java

```sh
javac PetitionGenerator.java

java PetitionGenerator [input file name]

```

Node.js

```sh
node PetitionGenerator [input file name] [count]
```

Python

```
python2 PetitionGenerator.py [count]
```

C

```
gcc PetitionGenerator.c -o PetitionGenerator
./PetitionGenerator [input file name] [count]
```

Ruby

```
./PetitionGenerator.py [input file name] [count]
```


where [input file name] contains a list of Chinese name, e.g.:

陳鑑林

梁家傑

黃國健

謝偉俊

胡志偉

黃毓民

梁美芬

蔣麗芸

and [count] is the number of signature to generate
