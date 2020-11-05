---
id: litvis

narrative-schemas:
  - ../../narrative-schemas/teaching.yml

elm:
  dependencies:
    gicentre/elm-vegalite: latest
    gicentre/tidy: latest
---

@import "../../css/datavis.less"

```elm {l=hidden}
import Tidy exposing (..)
import VegaLite exposing (..)
```

<!-- Everything above this line should probably be left untouched. -->

# Session 4: Practical Exercises

{(task|}

Use this document as a place to add your answers to the week's practical exercises.

{|task)}

#Exercise 1:

quantitive = numbers
orderable = easy to order
selective = can I easily pick out a specific group
associate = a variable is associative if we can perceive symbols as a group despite differences in this variable
dissociative = does a variable make a group seem less "visible"

|     Visual variable | quantitative | orderable | selective | associative | dissociative |
| ------------------: | :----------: | :-------: | :-------: | :---------: | :----------: |
|        _colour hue_ |      1       |     1     |     3     |      3      |      3       |
| _colour saturation_ |      1       |     3     |     3     |      3      |      3       |
|  _colour lightness_ |      1       |     3     |     3     |      3      |      3       |
|       _orientation_ |      1       |     1     |     3     |      3      |      1       |
|             _shape_ |      2       |     1     |     1     |      3      |      1       |
|           _texture_ |      1       |     1     |     2     |      2      |      1       |
|       _arrangement_ |      1       |     1     |     3     |      3      |      1       |
|              _size_ |      3       |     3     |     3     |      1      |      3       |
|             _focus_ |      1       |     3     |     3     |      3      |      3       |
|          _location_ |      3       |     3     |     3     |      2      |      2       |

#Exercise 2:

```elm {l}
vvTable =
    let
        table =
            """visualVariable,    q, o, s, a, d
               colour hue,        1, 1, 3, 3, 3
               colour saturation, 1, 3, 3, 3, 3
               colour lightness,  1, 3, 3, 3, 3
               orientation,       1, 1, 3, 3, 1
               shape,             2, 1, 1, 3, 1
               texture,           1, 1, 1, 1, 1
               arrangement,       1, 1, 3, 3, 1
               size,              3, 3, 3, 1, 3
               focus,             1, 3, 3, 3, 3
               location,          3, 3, 3, 1, 2"""
                |> fromCSV
                |> gather "property"
                    "rating"
                    [ ( "q", "quantitative" )
                    , ( "o", "orderable" )
                    , ( "s", "selectable" )
                    , ( "a", "associative" )
                    , ( "d", "dissociative" )
                    ]
    in
    dataFromColumns []
        << dataColumn "visualVariable" (table |> strColumn "visualVariable" |> strs)
        << dataColumn "property" (table |> strColumn "property" |> strs)
        << dataColumn "rating" (table |> numColumn "rating" |> nums)
```

```elm {l v}
summary : Spec
summary =
    let
        enc =
            encoding
                << position Y [ pName "visualVariable" ]
                << position X [ pName "property" ]
                << size
                    [ mName "rating"
                    , mScale
                        [ scRange (raNums [ 0, 1000 ])
                        , scType scPow
                        , scExponent 1
                        ]
                    ]
    in
    toVegaLite [ width 600, height 600, vvTable [], enc [], circle [] ]
```

I changed it by size as the variable was quantitative, which is best changed by size.

#Exercise 3:

```elm {l v}
healthField : Spec
healthField =
    let
        data =
            dataFromUrl "https://vega.github.io/vega-lite/data/gapminder-health-income.csv"

        enc =
            encoding
                << position X
                    [ pName "income", pScale [ scType scLog ], pAxis [ axGrid False ] ]
                << position Y
                    [ pName "health", pQuant, pScale [ scZero False ], pAxis [ axGrid False ] ]
                << size
                    [ mName "population"
                    , mScale
                        [ scRange (raNums [ 0, 5000 ])
                        , scType scPow
                        , scExponent 1.0
                        ]
                    ]
                << color [ mName "country" ]
    in
    toVegaLite [ width 500, height 350, data [], enc [], circle [] ]
```

Values are quantified so represented by size, the countries are nominal so distinguished by colour.

```elm {l v}
healthField2 : Spec
healthField2 =
    let
        data =
            dataFromUrl "https://vega.github.io/vega-lite/data/gapminder-health-income.csv"

        enc =
            encoding
                << position X
                    [ pName "income", pScale [ scType scLog ], pAxis [ axGrid False ] ]
                << position Y
                    [ pName "health", pQuant, pScale [ scZero False ], pAxis [ axGrid False ] ]
                << size
                    [ mName "population"
                    , mScale
                        [ scRange (raNums [ 0, 5000 ])
                        , scType scPow
                        , scExponent 1.0
                        ]
                    ]
                << shape [ mName "country" ]
    in
    toVegaLite [ width 500, height 350, data [], enc [], point [ maFilled True, maSize 200, maOpacity 0.5 ] ]
```

Use of shapes instead of colours, however I think that colours is more effective as it is more of selective visual variable.
