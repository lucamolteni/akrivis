#!/bin/sh

tmux new-session -d -s scorecard -n "Processor" 'cd akrivis-processor; mvn compile quarkus:dev'

tmux new-window -t scorecard -n "Database" 'cd database; docker-compose -f compose.yaml up'
tmux new-window -t scorecard -n "RestMock" 'cd examples/rest-service-mock; mvn compile quarkus:dev -Dquarkus.http.port=8081 -Ddebug=5006'
tmux new-window -t scorecard -n "Evaluator" 'cd akrivis-evaluator; mvn compile quarkus:dev -Dquarkus.http.port=8082 -Ddebug=5007'
tmux new-window -t scorecard -n "Ingestor" 'cd akrivis-ingestor; mvn compile quarkus:dev -Dquarkus.http.port=8083 -Ddebug=5008'
tmux new-window -t scorecard -n "Backstage" 'cd ../backstage-plugins; yarn start:backstage'


tmux attach-session -t scorecard
