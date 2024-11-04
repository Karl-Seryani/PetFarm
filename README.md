After cloning the repository on your machine run:
git checkout -b yourusername (put your username)

This will create a branch where you can make changes.

When you are ready to make a pull request, make sure to do the following:
git checkout dev
git pull
git checkout yourusername
git merge dev -m "message"
git push

This will include all the new changes from the dev branch into your branch so that when your pull request is approved, nothing is corrupted.