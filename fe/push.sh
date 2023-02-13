#!/bin/bash

export YARN=/usr/local/bin/yarn
export USER=ubuntu
export HOST=etherfirma.com
export DEST=/var/www/sec
export SCP=/usr/bin/scp

echo "$YARN builld"
echo "$SCP -r build/{*.ico,*.json,*.html,static} $USER@$HOST:$DEST"

$YARN build \
      && $SCP -r build/{*.ico,*.json,*.html,static} $USER@$HOST:$DEST

# EOF
