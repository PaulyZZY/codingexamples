from mailbox import linesep
from pydoc import plain, plainpager
import string
plainAlpha = string.ascii_uppercase
#User can type in the desired text file name with encrypted text messages.
file_name = input('Please indicate the encrypted text file name: \n')
first_decoded = input('Please input the frist sentence decoded: \n')
#Read the first line of encrypted message file to let the user determine what key is for this caesar cipher.
with open(file_name, 'r') as f:
    cipherText = f.readline()
actualKey = 0
for key in range(len(plainAlpha)):
    cipherAlpha = plainAlpha[key:] + plainAlpha[:key]
    tranTab = str.maketrans(plainAlpha,cipherAlpha)
    plainText = cipherText.translate(tranTab)
    # if plainText.replace('\n', '') == first_decoded.replace('\n',''):
    if plainText[0] == first_decoded[0][0]:
        actualKey = key
        break
    else:
        continue
    #print(f'Key number {key} : {plainText}')
#actualKey = input('Please indictae the key number where decrypted messgae is shown: ')
#Once the key is determined, read the whole text file to decrypted the whole message and print out decrypted texts.
actualyCipherAlpha = plainAlpha[int(actualKey):] + plainAlpha[:int(actualKey)]
actualTranTab = str.maketrans(plainAlpha,actualyCipherAlpha)
with open(file_name, 'r') as f1:
    decryptedText = f1.readlines()
#Create a new empty list with the same size of decryptedText, initialize it to all None.
decrypted = [None]*len(decryptedText)
for lines in range (len(decryptedText)):
        decrypted[lines] = str(decryptedText[lines]).translate(actualTranTab)
#Write the new list to file.
with open("decryptedMessage",'w') as d:
        d.writelines(decrypted)



