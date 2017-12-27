package it.test

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.common.FileSource
import com.github.tomakehurst.wiremock.extension.Extension
import com.github.tomakehurst.wiremock.extension.Parameters
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer
import com.github.tomakehurst.wiremock.http.Request
import com.github.tomakehurst.wiremock.http.RequestMethod
import com.github.tomakehurst.wiremock.http.ResponseDefinition
import groovy.json.JsonSlurper

class Transformer extends ResponseDefinitionTransformer implements Extension {

    ResponseDefinition transform(Request request, ResponseDefinition response, FileSource files, Parameters parameters) {
        if (request.method == RequestMethod.POST) {
            def bodyJson = new JsonSlurper().parseText(request.getBodyAsString())
            if (bodyJson.email) {
                return ResponseDefinitionBuilder
                        .like(response).but()
                        .withStatus(200)
                        .withBody("All mandatory fields exist")
                        .build()
            } else {
                ResponseDefinitionBuilder
                        .like(response).but()
                        .withStatus(400)
                        .withBody("No mandatory field Email")
                        .build()
            }
        } else {
            return response
        }
    }

    @Override
    String getName() {
        return "transformer"
    }
}
