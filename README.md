After cloning the repository on your machine run:
git checkout <yourname> (put your username)

This will create a branch where you can make changes.

When you are ready to make a pull request, make sure to do the following:
git checkout dev
git pull
git checkout <yourname>
git merge dev -m "<yourmessage>"
git push

This will include all the new changes from the dev branch into your branch so that when your pull request is approved, nothing is corrupted.