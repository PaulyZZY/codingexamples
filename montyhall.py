import random
win_count = 0
for run in range(10000):
    doors =[1,2,3]
    winningdoor = random.randint(1,3)
    #print(winningdoor)
    #Now the player makes his first pick.
    pick1 = random.randint(1,3)
    #print(pick1)
    # The host opens one door that the player did not choose and also not a winning door.
    reveal_door = 0
    for check in doors:
        if check != winningdoor and check != pick1:
            reveal_door = check
        else:
            continue
    #print(reveal_door)

    #Assume player always stays the same with his first choice
    if pick1 == winningdoor:
        win_count += 1
    else:
        continue

winning_probability = win_count/10000
print('The probability of winning if the player keeps his original choice is: ' + str(winning_probability*100) + '%')

# After increasing the sample run from 10,000 times to 1,000,000 times, the winning probability when the player keeps
# the original choice is always around 33%, which indicates the player will have a better chance winning if switch choice.