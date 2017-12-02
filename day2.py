
f = open('d2.txt')
s=0
for line in f.readlines():
    line=[int(x) for x in line.strip().split()]
    s = s + max(line)-min(line)
print(s)
    
    
l=[5,9,2,8]

f = open('d2.tes')
s=0
for line in f.readlines():
    line=[int(x) for x in line.strip().split()]
    l = [ (x,y) for x in line for y in line]
    l2 = [ (x,y) for x,y in l if x!=y and (x%y==0 or y%x==0)]
    s = s + xmax([ x/y for x,y in l2  ])
print(s)

