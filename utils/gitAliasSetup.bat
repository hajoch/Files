git config --global alias.co checkout
git config --global alias.ci commit
git config --global alias.st status
git config --global alias.br branch

REM 	Add all uncommited and un-staged changes to previous commit
git config --global alias.amend "commit -a --amend -C HEAD"

REM 	Assuming and unassuming files as unchanged
git config --global alias.assume "update-index --assume-unchanged"
git config --global alias.unassume "update-index --no-assume-unchanged"
git config --global alias.assumed "!git ls-files -v | grep ^h | cut -c 3-"
git config --global alias.unassumeall "!git assumed | xargs git update-index --no-assume-unchanged"


REM 	List all aliases
git config --global alias.aliases "!git config -l | grep alias | cut -c 7-"

REM 	Search the repository for file
git config --global alias.find "!git ls-files | grep -i"

REM 	Listing commit history
git config --global alias.ll "log --pretty=format:'%%C(yellow)%%h%%Cred%%d %%Creset%%s%%Cblue [%%cn]' --decorate --numstat"
git config --global alias.ls "log --pretty=format:'%%C(yellow)%%h%%Cred%%d %%Creset%%s%%Cblue [%%cn]' --decorate"
git config --global alias.last "log -1 HEAD"
git config --global alias.graph "log --graph --full-history --all --pretty=format:'%%h%%x09%%d%%x20%%s'"


REM 	To make sure the working directory is up to date before creating branch
git config --global alias.up "!git pull --rebase --prune $@ && git submodule update --init --recursive"

REM 	Temporary commit save to not lose work, then undo to continue working
git config --global alias.save "!git add -A && git commit -m 'SAVE'"
git config --global alias.undo "reset HEAD~1 --mixed"

REM 	instead of reset hard, create hidden commit fetchable from the reflog
git config --global alias.wipe "!git add -A && git commit -qm 'WIPE SAVE' && git reset HEAD~1 --hard"


REM 	deletes every branch thats been merged into master except master
REM 	NB: To use e.g development branch instead:  git bdone development
git config --global alias.bclean "!f() { git branch --merged ${1-master} | grep -v ' ${1-master}$' | xargs -r git branch -d; }; f"
REM 	calls bclean and changes to master/(argument) branch
git config --global alias.bdone "!f() { git checkout ${1-master} && git bclean ${1-master}; }; f"

REM 	List repo contributors
git config --global alias.contributors "!git shortlog -n -s --no-merges $@ | cat - && echo && echo total $(git rev-list --count HEAD)"

REM 	Show all branches not merged into current branch (HEAD)
git config --global alias.unmerged "!git fetch --all && git branch --all --no-merged HEAD"
git config --global alias.merged "!git fetch --all && git branch --all --merged HEAD"