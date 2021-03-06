package com.conveyal.gtfs.api.graphql;

import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.LineString;
import graphql.schema.Coercing;

import java.util.stream.Stream;

/**
 * Created by matthewc on 3/9/16.
 */
public class GeoJsonCoercing implements Coercing {
    @Override
    public Object serialize(Object input) {
        if (input instanceof LineString) {
            LineString g = (LineString) input;
            GeoJsonLineString ret = new GeoJsonLineString();
            ret.coordinates = Stream.of(g.getCoordinates())
                    .map(c -> new double[] { c.x, c.y })
                    .toArray(i -> new double[i][]);

            return ret;
        }
        else return null;
    }

    @Override
    public Object parseValue(Object o) {
        return null;
    }

    @Override
    public Object parseLiteral(Object o) {
        return null;
    }

    private static class GeoJsonLineString {
        public final String type = "LineString";
        public double[][] coordinates;
    }
}
