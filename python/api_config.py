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
    "label": "points"
}]

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
        "eventType": "comment"
        },
    "then": {
        "action": {
            "name": "addPoint",
            "target": "point",
            "amount": 1
            }
        }
    }
]