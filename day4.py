s = 0
for line in open("d4.txt").readlines():
    l = line.strip().split()
    if len(l) == len(set(l)):
        s = s + 1

print(s)


s = 0
for line in open("d4.txt").readlines():
    l = line.strip().split()
    l = ["".join(sorted(x)) for x in l]
    if len(l)==len(set(l)):
        s = s + 1

print(s)
