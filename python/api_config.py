# ---------------------------------------------------------------------------- #
#               Contain default template for the setup of the API              #
# ---------------------------------------------------------------------------- #

APPLICATION_NAME = "Gusamaal"

BADGES = [{
    "name": "terre",
    "color": "brun",
    "description": "badge debutant"
},{
    "name": "feu",
    "color": "rouge",
    "description": "badge intermediaire"
},{
    "name": "ciel",
    "color": "bleu",
    "description": "badge avance"
}]

POINTSCALES = [{
        "label": "question"
    },
    {
        "label": "comment"
    },
    {
        "label": "answer"
    },
    {
        "label": "vote"
    }
]

RULES = [{
    "if": {
        "eventType": "comment"
        },
    "then": {
        "action": {
            "name": "addBadge",
            "target": "ciel",
            "amount": 0
            }
        }
    },{
    "if": {
        "eventType": "question"
        },
    "then": {
        "action": {
            "name": "addPoint",
            "target": "question",
            "amount": 1
            }
        }
    },{
    "if": {
        "eventType": "comment"
        },
    "then": {
        "action": {
            "name": "addPoint",
            "target": "comment",
            "amount": 1
            }
        }
    },{
    "if": {
        "eventType": "answer"
        },
    "then": {
        "action": {
            "name": "addPoint",
            "target": "answer",
            "amount": 1
            }
        }
    },{
    "if": {
        "eventType": "vote"
        },
    "then": {
        "action": {
            "name": "addPoint",
            "target": "vote",
            "amount": 1
            }
        }
    }
]