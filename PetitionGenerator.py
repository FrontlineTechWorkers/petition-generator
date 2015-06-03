import random
import sys

count = int(sys.argv[1])

names = open('rawname.txt').readlines()
names = [n.strip().decode('utf-8') for n in names]
name_parts = [(n[0], n[1:]) for n in names]
last, first = zip(*name_parts)
last = ''.join(last)
first = ''.join(first)
for i in xrange(count):
    print random.choice(last) + random.choice(first) + random.choice(first)
