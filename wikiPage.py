import bs4
from bs4 import BeautifulSoup
import requests
import webbrowser
continueSearch = True
while continueSearch:
    randomURL = requests.get("https://en.wikipedia.org/wiki/Special:Random")
    soup = BeautifulSoup(randomURL.content,"html.parser")
    articleTitle = soup.find(id = 'firstHeading').text
    answer = input("Do you want to read the article named "+str(articleTitle)+'?')
    if answer == 'yes':
        url = 'https://en.wikipedia.org/wiki/'+str(articleTitle)
        webbrowser.open(url)
        continueSearch = False
        break
    elif answer == 'no':
        continue
    else:
        print('Please answer yes or no.')
        continue

