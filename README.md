name: build
on: [push, workflow_dispatch]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with: { distribution: temurin, java-version: 21 }
      - uses: gradle/actions/setup-gradle@v4
        with: { gradle-version: "8.11" }
      - name: Build
        run: |
          gradle build --console=plain --no-daemon > build.log 2>&1 || {
            echo '## Build errors' >> $GITHUB_STEP_SUMMARY
            echo '```' >> $GITHUB_STEP_SUMMARY
            tail -80 build.log >> $GITHUB_STEP_SUMMARY
            echo '```' >> $GITHUB_STEP_SUMMARY
            exit 1
          }
      - uses: actions/upload-artifact@v4
        with:
          name: donutvisual-jar
          path: build/libs/*.jar
