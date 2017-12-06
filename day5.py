l =[ int(x) for x in  open("d5.txt").read().strip().split()]

i = 0
steps = 0
while True:
    try:
        oi = i
        jump_step = l[i]
        i = i + l[i]
        if jump_step <3:
            l[oi] = l[oi] + 1 
        else:
            l[oi] = l[oi] - 1
        steps = steps + 1
    except:
        break
print(steps)
