package heigit.ors.routing.graphhopper.extensions.flagencoders.nextgen;

import org.junit.Assert;
import org.junit.Test;

public class BikeSpeedOverwriteTest {

    final NextGenBikeCommonFlagEncoder enc;
    
    public BikeSpeedOverwriteTest() {
        enc = new NextGenRoadBikeFlagEncoder();

        enc.setHighwaySpeed("unclassified",25);
        enc.setHighwaySpeed("maxima",      45);
        enc.setHighwaySpeed("minima",      2);
        enc.setHighwaySpeed("cycleway15",     new NextGenRoadBikeFlagEncoder.SpeedValue(15, NextGenRoadBikeFlagEncoder.UpdateType.DOWNGRADE_ONLY));
        enc.setHighwaySpeed("highway30",      new NextGenRoadBikeFlagEncoder.SpeedValue(30, NextGenRoadBikeFlagEncoder.UpdateType.UPGRADE_ONLY));

        enc.setSurfaceSpeed("hyper35",    new NextGenRoadBikeFlagEncoder.SpeedValue(35, NextGenRoadBikeFlagEncoder.UpdateType.UPGRADE_ONLY));
        enc.setSurfaceSpeed("glue10",     new NextGenRoadBikeFlagEncoder.SpeedValue(10, NextGenRoadBikeFlagEncoder.UpdateType.DOWNGRADE_ONLY));
        enc.setSurfaceSpeed("atomicUP40",  40);
        enc.setSurfaceSpeed("atomicDOWN05",5);


        enc.setHighwaySpeed("cycleway",    new NextGenRoadBikeFlagEncoder.SpeedValue(8, NextGenRoadBikeFlagEncoder.UpdateType.DOWNGRADE_ONLY));
        enc.setSurfaceSpeed("asphalt",      new NextGenRoadBikeFlagEncoder.SpeedValue(18, NextGenRoadBikeFlagEncoder.UpdateType.UPGRADE_ONLY));
        enc.setSurfaceSpeed("gravel",       new NextGenRoadBikeFlagEncoder.SpeedValue(5, NextGenRoadBikeFlagEncoder.UpdateType.DOWNGRADE_ONLY));
        enc.setSurfaceSpeed("gravel2",      5);
    }

    @Test
    public void TestFinalHighwaySpeedsBasedOnSurfaces() {
        Assert.assertEquals(35, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("unclassified"), enc.getSurfaceSpeed("hyper35")));
        Assert.assertEquals(10, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("unclassified"), enc.getSurfaceSpeed("glue10")));
        Assert.assertEquals(40, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("unclassified"), enc.getSurfaceSpeed("atomicUP40")));
        Assert.assertEquals( 5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("unclassified"), enc.getSurfaceSpeed("atomicDOWN05")));

        Assert.assertEquals(45, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("maxima"), enc.getSurfaceSpeed("hyper35")));
        Assert.assertEquals(10, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("maxima"), enc.getSurfaceSpeed("glue10")));
        Assert.assertEquals(40, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("maxima"), enc.getSurfaceSpeed("atomicUP40")));
        Assert.assertEquals( 5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("maxima"), enc.getSurfaceSpeed("atomicDOWN05")));

        Assert.assertEquals(35, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("minima"), enc.getSurfaceSpeed("hyper35")));
        Assert.assertEquals( 2, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("minima"), enc.getSurfaceSpeed("glue10")));
        Assert.assertEquals(40, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("minima"), enc.getSurfaceSpeed("atomicUP40")));
        Assert.assertEquals( 5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("minima"), enc.getSurfaceSpeed("atomicDOWN05")));

        Assert.assertEquals(15, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway15"), enc.getSurfaceSpeed("hyper35")));
        Assert.assertEquals(10, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway15"), enc.getSurfaceSpeed("glue10")));
        Assert.assertEquals(15, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway15"), enc.getSurfaceSpeed("atomicUP40")));
        Assert.assertEquals( 5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway15"), enc.getSurfaceSpeed("atomicDOWN05")));

        Assert.assertEquals(35, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("highway30"), enc.getSurfaceSpeed("hyper35")));
        Assert.assertEquals(30, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("highway30"), enc.getSurfaceSpeed("glue10")));
        Assert.assertEquals(40, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("highway30"), enc.getSurfaceSpeed("atomicUP40")));
        Assert.assertEquals(30, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("highway30"), enc.getSurfaceSpeed("atomicDOWN05")));

        Assert.assertEquals(5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway"), enc.getSurfaceSpeed("gravel")));
        Assert.assertEquals(5, enc.calcHighwaySpeedBasedOnSurface(enc.getHighwaySpeed("cycleway"), enc.getSurfaceSpeed("gravel2")));
    }
}
