# Créé par Tom, le 11/05/2017 en Python 3.2
from tkinter import *


""" Ecran principal """

fen = Tk()
fen.title("Aperçu")
jeu = Canvas(fen, width =845, height =640)


titlescreen = PhotoImage(file= '../backgrounds/titlescreenFinal.gif')
rules = PhotoImage(file= '../boutons/buttonrules.gif')
play = PhotoImage(file= '../boutons/buttonplay.gif')
quitt = PhotoImage(file= '../boutons/buttonquit.gif')

item=jeu.create_image(423,320,image=titlescreen)

item=jeu.create_image(423,580,image=play)
item=jeu.create_image(275,580,image=rules)
item=jeu.create_image(556,580,image=quitt)

jeu.grid()
fen.mainloop()


""" Ecran règles """

fen2 = Tk()
fen2.title("Rules")
jeu2 = Canvas(fen2, width =845, height =640)

rulesbackground = PhotoImage(file= '../backgrounds/rules.gif')
back = PhotoImage(file= '../boutons/buttonback.gif')

item=jeu2.create_image(423,320,image=rulesbackground)
item=jeu2.create_image(423,580,image=back)
blabla ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur a sapien nec nulla maximus tempor sit amet sed enim. Fusce eu ante libero. In libero augue, condimentum ac finibus quis, gravida ac tellus. Maecenas varius tellus quis dolor finibus, eget mollis mauris malesuada. Phasellus maximus orci eu pulvinar tempus. Sed malesuada ante a nisi condimentum luctus nec id dolor. Sed finibus vulputate lorem, eu malesuada neque mattis id. Donec dignissim, nisl vitae ullamcorper scelerisque, lorem ipsum vestibulum urna, in tincidunt ante dolor sed neque. Sed ac sodales enim. Nam ipsum metus, lacinia at mi id, maximus porta arcu. Nullam quis sapien maximus, vehicula dui quis, finibus magna.\n Mauris et dui in enim maximus pharetra. Cras elementum tincidunt velit at rhoncus. Morbi sit amet nulla a arcu convallis aliquam scelerisque vitae nisi. Nam at magna mauris. Praesent nec ante faucibus, vestibulum nibh ac, sollicitudin nunc. Phasellus ut erat ac velit tincidunt volutpat. Nunc neque est, fermentum in lacinia quis, efficitur vel turpis. Ut molestie consectetur quam non sollicitudin. Suspendisse ut diam id enim vehicula pellentesque. Proin euismod, ante nec bibendum sagittis, lacus ipsum volutpat tortor, ac egestas nunc ex id dui. Sed ac ex sed nunc facilisis consectetur. Curabitur rhoncus leo ac tortor cursus, id consectetur magna Pellentesque porta sapien libero, sit amet tristique metus gravida congue. Nunc sollicitudin, turpis quis imperdiet facilisis, ligula tellus."
texte = jeu2.create_text(423, 320, text=blabla, fill='green', width=500)

jeu2.grid()
fen2.mainloop()



""" Ecran jeu """

fen3 = Tk()
fen3.title("Jeu")
jeu3 = Canvas(fen3, width =845, height =640)

background = PhotoImage(file= '../backgrounds/gamebackground.gif')


item=jeu3.create_image(423,320,image=background)



jeu3.grid()
fen3.mainloop()




