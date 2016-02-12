git config --global user.name "Hallvard Jore Christensen"
git config --global user.email "hallvard.jore.christensen@gmail.com"

git config --global core.whitespace trailing-space,space-before-tab
git config --global color.ui auto
git config --global diff.renames copies

REM 	diff uses mnemonic prefixes instead of the standard a and b notation
git config --global diff.mnemonicprefix true

REM 	Record resolved conflicts
git config --global rerere.enabled true