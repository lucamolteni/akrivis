package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sonarcloud")
public class SonarcloudResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sonarcloud() {
        return """
                {
                  "paging": {
                    "pageIndex": 1,
                    "pageSize": 100,
                    "total": 3
                  },
                  "baseComponent": {
                    "key": "MY_PROJECT",
                    "name": "My Project",
                    "qualifier": "TRK",
                    "measures": [
                      {
                        "metric": "new_violations",
                        "periods": [
                          {
                            "index": 1,
                            "value": "255"
                          }
                        ]
                      },
                      {
                        "metric": "complexity",
                        "value": "42"
                      },
                      {
                        "metric": "ncloc",
                        "value": "1984"
                      }
                    ]
                  },
                  "components": [
                    {
                      "key": "com.sonarsource:java-markdown:src/main/java/com/sonarsource/markdown/impl/ElementImpl.java",
                      "name": "ElementImpl.java",
                      "qualifier": "FIL",
                      "language": "java",
                      "path": "src/main/java/com/sonarsource/markdown/impl/ElementImpl.java",
                      "measures": [
                        {
                          "metric": "new_violations",
                          "periods": [
                            {
                              "index": 1,
                              "value": "25"
                            }
                          ]
                        },
                        {
                          "metric": "complexity",
                          "value": "12"
                        },
                        {
                          "metric": "ncloc",
                          "value": "114"
                        }
                      ]
                    },
                    {
                      "key": "com.sonarsource:java-markdown:src/test/java/com/sonarsource/markdown/impl/ElementImplTest.java",
                      "name": "ElementImplTest.java",
                      "qualifier": "UTS",
                      "language": "java",
                      "path": "src/test/java/com/sonarsource/markdown/impl/ElementImplTest.java",
                      "measures": [
                        {
                          "metric": "new_violations",
                          "periods": [
                            {
                              "index": 1,
                              "value": "0"
                            }
                          ]
                        }
                      ]
                    },
                    {
                      "key": "com.sonarsource:java-markdown:src/main/java/com/sonarsource/markdown/impl",
                      "name": "src/main/java/com/sonarsource/markdown/impl",
                      "qualifier": "DIR",
                      "path": "src/main/java/com/sonarsource/markdown/impl",
                      "measures": [
                        {
                          "metric": "new_violations",
                          "periods": [
                            {
                              "index": 1,
                              "value": "25"
                            }
                          ]
                        },
                        {
                          "metric": "complexity",
                          "value": "35"
                        },
                        {
                          "metric": "ncloc",
                          "value": "217"
                        }
                      ]
                    }
                  ],
                  "metrics": [
                    {
                      "key": "complexity",
                      "name": "Complexity",
                      "description": "Cyclomatic complexity",
                      "domain": "Complexity",
                      "type": "INT",
                      "higherValuesAreBetter": false,
                      "qualitative": false,
                      "hidden": false,
                      "custom": false
                    },
                    {
                      "key": "ncloc",
                      "name": "Lines of code",
                      "description": "Non Commenting Lines of Code",
                      "domain": "Size",
                      "type": "INT",
                      "higherValuesAreBetter": false,
                      "qualitative": false,
                      "hidden": false,
                      "custom": false
                    },
                    {
                      "key": "new_violations",
                      "name": "New issues",
                      "description": "New Issues",
                      "domain": "Issues",
                      "type": "INT",
                      "higherValuesAreBetter": false,
                      "qualitative": true,
                      "hidden": false,
                      "custom": false,
                      "bestValue": "0"
                    }
                  ],
                  "periods": [
                    {
                      "index": 1,
                      "mode": "previous_version",
                      "date": "2016-01-11T10:49:50+0100",
                      "parameter": "1.0-SNAPSHOT"
                    }
                  ]
                }""";
    }
}
