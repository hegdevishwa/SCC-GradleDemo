package contracts.greeting

import static org.springframework.cloud.contract.spec.Contract.make

[
    make {
        request {
            name("Greet")
            method 'GET'
            urlPath('/greet')
        }
        response {
            status OK()
            body('greeting': "Hello world!")
        }
    }
]