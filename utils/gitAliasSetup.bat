git config --global alias.co checkout
&& git config --global alias.ci commit
&& git config --global alias.st status
&& git config --global alias.br branch

&& git config --global alias.assume "update-index --assume-unchanged"
&& git config --global alias.unassume "update-index --no-assume-unchanged"
&& git config --global alias.assumed "!git ls-files -v | grep ^h | cut -c 3-"
&& git config --global alias.unassumeall "!git assumed | xargs git update-index --no-assume-unchanged"

&& git config --global alias.last "log -1 HEAD"
&& git config --global alias.graph "log --graph --full-history --all --pretty=format:'%h%x09%d%x20%s'"
&& git config --global alias.aliases "!git config -l | grep alias | cut -c 7-"
&& git config --global alias.find "!git ls-files | grep -i"

&& git config --global alias.ll "log --pretty=format:'%C(yellow)%h%Cred%d\\ %Creset%s%Cblue\\ [%cn]' --decorate --numstat"
&& git config --global alias.ls "log --pretty=format:'%C(yellow)%h%Cred%d\\ %Creset%s%Cblue\\ [%cn]' --decorate"