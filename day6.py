l = [ int(x) for x in open("d6.txt").read().strip().split()]
print(l)
d = {}
steps = 0
key = tuple(l)
llen = len(l)
while key not in d:
    d[key]=steps
    steps = steps + 1
    biggest = max(l)
    i = l.index(biggest)
    l[i]=0
    ti = i
    for x in range(biggest):
        ti = (ti + 1)%llen
        l[ti] = l[ti] + 1
    key = tuple(l)
print(key,steps,steps - d[key])
    
