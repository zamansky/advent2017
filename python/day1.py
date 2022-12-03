f = open("input.txt")
origdata = f.readline()

origdata = origdata.strip()
data = origdata

data = data + data[0]

s = 0
l = len(data)
for i in range(l-1):
    if data[i]==data[i+1]:
        s = s + int(data[i])
print("loop version: ",s)
        
nl = sum([ int(a) for a,b in zip(data,data[1:]) if a==b])
print(nl)

data = origdata
s = 0
l = len(data)
for i in range(l-1):
    if data[i]==data[(i+l//2)%l]:
        s = s + int(data[i])
print("part 2loop version: ",s)

data = origdata
l = len(data)
d2 = data[l//2:]+data
nl = sum([ int(a) for a,b in zip(data,d2)if a==b])
print(nl)
